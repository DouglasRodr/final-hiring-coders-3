import React, { useEffect } from 'react'
import { useProduct } from "vtex.product-context";
import { ExtensionPoint } from 'vtex.render-runtime';
import useProductSuggestions from '../hooks/useProductSuggestions';

import productsByIdentifier from "../graphql/productsByIdentifier.graphql";
import { useLazyQuery } from 'react-apollo';

enum ProductUniqueIdentifierField {
  id = "id",
  slug = "slug",
  ean = "ean",
  reference = "reference",
  sku = "sku",
}

interface Props {
  children?: JSX.Element | JSX.Element[];
}

function BuyTogetherSuggestions({ children }: Props) {
  const productContext = useProduct()!;

  const productId = React.useMemo(
    () => Number(productContext.product?.productId), [productContext]
  )

  const {
    productSuggestions,
    loadingSuggestions,
    fetchProductSuggestions,
  } = useProductSuggestions();

  const [
    queryProductsById,
    { data: products, called: productsByIdCalled, loading: loadingProductsById },
  ] = useLazyQuery(productsByIdentifier, { notifyOnNetworkStatusChange: true });

  useEffect(() => {
    fetchProductSuggestions(productId);
  }, [productId]);

  useEffect(() => {
    if (!productSuggestions) {
      return;
    }

    const productSuggestionsIds = productSuggestions.map(
      (productSuggestion) => productSuggestion.id
    );

    if (productSuggestionsIds.length === 0) {
      return;
    }

    const executeQuery = (variables: Record<string, any>) =>
      queryProductsById({
        variables,
      });

    executeQuery({
      field: ProductUniqueIdentifierField.id,
      values: productSuggestionsIds,
    });
  }, [productSuggestions]);

  return <>
    {productsByIdCalled && !loadingProductsById && !loadingSuggestions &&
      products && products.productsByIdentifier &&
      products.productsByIdentifier.length > 0 && (
        <>
          {children}
          <ExtensionPoint id="buy-together-custom" suggestedProducts={[products.productsByIdentifier]} />
        </>
      )
    }
  </>
}

export default BuyTogetherSuggestions;
