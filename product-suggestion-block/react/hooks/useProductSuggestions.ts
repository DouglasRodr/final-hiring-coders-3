import { useCallback, useState } from "react";
import { Products } from "../service/@types";
import { SuggestionService } from "./../service";

export default function useProductSuggestions() {
  const [productSuggestions, setProductSuggestions] = useState<
    Products.Suggestion[]
  >([]);

  const [loadingSuggestions, setLoadingSuggestions] = useState(false);
  const [errorSuggestions, setErrorSuggestions] = useState(false);

  const fetchProductSuggestions = useCallback((productId: number) => {
    setLoadingSuggestions(true);
    SuggestionService.getSuggestionsByProductId(productId)
      .then((productSuggestions) => {
        setProductSuggestions(productSuggestions);
        setErrorSuggestions(false);
      })
      .catch(() => setErrorSuggestions(true))
      .finally(() => setLoadingSuggestions(false));
  }, []);

  return {
    productSuggestions,
    loadingSuggestions,
    errorSuggestions,
    fetchProductSuggestions,
  };
}
