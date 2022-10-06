import React, { useMemo } from 'react'

import { FormattedCurrency } from 'vtex.format-currency'
import { useCssHandles } from 'vtex.css-handles'

interface Props {
  cartItems: CartItem[]
  BuyButton: React.ComponentType<{ skuItems: CartItem[] }>
}

const CSS_HANDLES = [
  'totalSuggestionContainer',
  'totalSuggestionTitle',
  'totalSuggestionValue',
]

export default function TotalSuggestion({
  cartItems,
  BuyButton
}: Props) {

  const handles = useCssHandles(CSS_HANDLES)

  const totalProducts = cartItems.length
  const totalPrice = useMemo(() => {
    return cartItems.reduce((total: number, currentItem: CartItem) => {
      return total + currentItem.sellingPrice / 100
    }, 0)
  }, [cartItems])

  return (
    <div className={`w-100 mh2 mh6-l w-20-l self-center mv4 ${handles.totalSuggestionContainer}`}>
      <div className={`mv5 ${handles.totalSuggestionTitle}`}>
        {`Compre os ${totalProducts} produtos por`}
      </div>
      <div className={`mv5 ${handles.totalSuggestionValue}`}>
        <FormattedCurrency value={totalPrice} />
      </div>
      <BuyButton skuItems={cartItems} />
    </div>
  )
}
