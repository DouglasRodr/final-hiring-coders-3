import React, { useState, Fragment, useMemo, useRef } from 'react'
import { IconPlusLines } from 'vtex.styleguide'
import { useProduct } from 'vtex.product-context'
import { ExtensionPoint } from 'vtex.render-runtime'
import ProductSummary from 'vtex.product-summary/ProductSummaryCustom'
import { ProductGroupContext } from 'vtex.product-group-context'
import { useCssHandles } from 'vtex.css-handles'

import { mapSKUItemsToCartItems, sortItemsByLists } from '../utils'
import ProductSummaryWithChange from './ProductSummaryWithChange'
import TotalSuggestion from './TotalSuggestion'

import IconEqual from '../icons/IconEqual'

const { ProductGroupProvider, useProductGroup } = ProductGroupContext

interface Props {
  suggestedProducts?: Product[][]
  BuyButton: React.ComponentType<{ skuItems: CartItem[] }>
}

const CSS_HANDLES = [
  'buyTogetherContainer',
  'buyTogetherContent',
  'buyTogetherSuggestion',
  'buyTogetherIconPlus',
  'buyTogetherIconEqual'
]

const notNull = (item: CartItem | null): item is CartItem => item !== null

function BuyTogetherCustom({
  suggestedProducts,
  BuyButton
}: Props) {
  const handles = useCssHandles(CSS_HANDLES)
  const ref = useRef<HTMLDivElement | null>(null)
  const { product: baseProduct } = useProduct() as any
  const { items } = useProductGroup()!

  const [suggestedLists, setSuggestedLists] = useState<SuggestedList[]>(
    suggestedProducts?.map(list => {
      return { products: list, hidden: false, current: 0 }
    }) ?? []
  )

  const normalizedBaseProduct = useMemo(
    () => ProductSummary.mapCatalogProductToProductSummary(baseProduct),
    [baseProduct]
  )

  const filteredItems = useMemo(() => {
    const sortedItems = sortItemsByLists(items, suggestedLists)
    return sortedItems.filter((item, index) => {
      if (index === 0) {
        return true
      }
      if (item.quantity === 1) {
        return !suggestedLists[index - 1].hidden
      }
      return !(suggestedLists[index - 1].hidden && suggestedLists[index].hidden)
    })
  }, [items, suggestedLists])

  const onChangeProduct = (index: number) => {
    const newSuggestedLists = suggestedLists.map((list, listIndex) => {
      if (listIndex !== index) {
        return list
      }
      const current =
        list.current + 1 < list.products.length ? list.current + 1 : 0
      return { ...list, current }
    })
    setSuggestedLists(newSuggestedLists)
  }

  const cartItems = useMemo(() => {
    return mapSKUItemsToCartItems(filteredItems).filter(notNull)
  }, [filteredItems])

  if (!suggestedLists.length) {
    return null
  }

  return (
    <div className={`flex-none tc ${handles.buyTogetherContainer}`} ref={ref}>
      <div className={`flex flex-column flex-row-l justify-between ${handles.buyTogetherContent}`}>
        <div className={`w-100 w-60-l flex flex-row items-center-ns justify-center ${handles.buyTogetherSuggestion}`}>
          <div>
            <ExtensionPoint
              id="product-summary"
              product={normalizedBaseProduct}
            />
          </div>

          {suggestedLists.map((suggestedList, index) => {
            const { products, current } = suggestedList
            return (
              <Fragment key={`${products[current]?.productId}-${index}`}>
                <div className={`self-center ma2 ${handles.buyTogetherIconPlus}`}>
                  <IconPlusLines size={20} />
                </div>
                <ProductSummaryWithChange
                  index={index}
                  hidden={suggestedList.hidden}
                  hideChangeAction={products.length <= 1}
                  onChangeProduct={onChangeProduct}
                  product={products[current]}
                />
              </Fragment>
            )
          })}
        </div>
        <div className={`self-center ma5 mv5 ${handles.buyTogetherIconEqual}`}>
          <IconEqual />
        </div>
        <TotalSuggestion cartItems={cartItems} BuyButton={BuyButton} />
      </div >
    </div >
  )
}

export default function EnhancedBuyTogether(props: Props) {
  return (
    <ProductGroupProvider>
      <BuyTogetherCustom {...props} />
    </ProductGroupProvider>
  )
}
