import React, { useMemo } from 'react'
import { ExtensionPoint } from 'vtex.render-runtime'
import ProductSummary from 'vtex.product-summary/ProductSummaryCustom'
import { useCssHandles } from 'vtex.css-handles'

import ChangeSuggestionButton from './ChangeSuggestionButton'

interface Props {
  product: Product
  index: number
  hidden?: boolean
  hideChangeAction?: boolean
  onChangeProduct: (index: number) => void
}

const CSS_HANDLES = [
  'suggestionContainer',
  'changeSuggestionContainer'
]

export default function ProductSummaryWithChange({ product, index, hideChangeAction, onChangeProduct }: Props) {

  const handles = useCssHandles(CSS_HANDLES)

  const normalizedProduct = useMemo(
    () => ProductSummary.mapCatalogProductToProductSummary(product),
    [product]
  )

  return (
    <div className={`flex flex-column ${handles.suggestionContainer}`}>
      <div className={`tc${handles.changeSuggestionContainer}`}>
        <ChangeSuggestionButton hideChangeAction={hideChangeAction} index={index} onChangeProduct={onChangeProduct} />
      </div>
      <ExtensionPoint
        id="product-summary"
        product={normalizedProduct}
      />
    </div>
  )
}
