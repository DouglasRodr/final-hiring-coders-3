import React, { Fragment, useContext, useEffect, useState } from 'react'
import { Spinner, EmptyState } from 'vtex.styleguide'

import { getAllSuggestions } from '../../api'
import { AuthContext, AuthType } from '../../context/authContext'
import Suggestion from './suggestion'
import { ProductType } from '../products-table'

type Props = {
  product: ProductType
}

export type SuggestionType = {
  id: number
  name: string
  image?: string
  isActive?: boolean
  category?: string
  brand?: string
}

const ProductData: React.FC<Props> = ({ product }) => {
  const { token } = useContext(AuthContext) as AuthType
  const [suggestions, setSuggestions] = useState<SuggestionType[]>([])
  const [loading, setLoading] = useState<boolean>(true)

  const fetchSuggestions = async () => {
    const { content } = await getAllSuggestions(token, product.id)

    content.map(
      async (item: {
        combinedProductId: number
        combinedProductName: string
        combinationActive: boolean
      }) => {
        const itemInfo = await fetch(
          `/api/catalog_system/pub/products/variations/${item.combinedProductId}`
        )

        const json = await itemInfo.json()

        const itemData: SuggestionType = {
          id: json.productId,
          name: json.name,
          image: json.skus[0].image,
          isActive: item.combinationActive,
        }

        setSuggestions((prevProducts) => [...prevProducts, itemData])
      }
    )
    setLoading(false)
  }

  useEffect(() => {
    fetchSuggestions()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  return (
    <div className="flex flex-column justify-center">
      <div className="flex items-center justify-start">
        <div className="w-25">
          <img src={product.image} alt={product.name} className="w100" />
        </div>
        <div className="flex flex-column items-start justify-start pv4">
          <div className="pa2">
            <span className="db fw1 pv4">Nome do Produto:</span>
            <span className="db fw5">{product.name}</span>
          </div>
          <div className="pa2">
            <span className="db fw1 pv4">Unidades Vendidas:</span>
            <strong className="db fw5">{product.quantitySold}</strong>
          </div>
        </div>
      </div>
      <div className="flex flex-column items-center">
        {loading ? (
          <Spinner />
        ) : (
          <Fragment>
            <div className="flex items-center justify-between w-100 bb fw3 b--black-05 pb2 mt2">
              <strong>Combina????es Dispon??veis</strong>
              <strong>Ativar/Desativar</strong>
            </div>
            {suggestions.length > 0 ? (
              suggestions.map((suggestion) => (
                <Suggestion
                  key={suggestion.id}
                  suggestion={suggestion}
                  productId={product.id}
                />
              ))
            ) : (
              <EmptyState title="Nenhuma sugest??o dispon??vel">
                <p>
                  N??o encontramos nenhuma sugest??o para o produto selecionado.
                </p>
              </EmptyState>
            )}
          </Fragment>
        )}
      </div>
    </div>
  )
}

export default ProductData
