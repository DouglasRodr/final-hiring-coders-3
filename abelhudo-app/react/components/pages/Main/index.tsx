import React, { Fragment, useContext, useEffect, useState } from 'react'
import { Spinner, InputSearch } from 'vtex.styleguide'

import { getAllProducts, authenticate } from '../../../api'
import { AuthContext, AuthType } from '../../../context/authContext'
import Table from '../../products-table'

interface ProductType {
  id: number
  name: string
  active: boolean
  quantitySold: number
  image?: string
}

const Main: React.FC = () => {
  const [products, setProducts] = useState<ProductType[]>([])
  const [search, setSearch] = useState<string>('')
  const { token, updateToken } = useContext(AuthContext) as AuthType

  const getProducts = async () => {
    if (token) {
      const { content } = await getAllProducts(token, search)

      setProducts(content)
    }
  }

  const getAuth = async () => {
    const response = await authenticate()

    updateToken(response.access_token)
  }

  useEffect(() => {
    if (token) {
      getProducts()
    } else {
      getAuth()
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [token])

  const updateSearch = (e) => {
    setSearch(e.target.value)
    getProducts()
  }

  return (
    <Fragment>
      <InputSearch
        placeholder="Busque seu produto aqui..."
        value={search}
        size="regular"
        onChange={(e) => {
          updateSearch(e)
        }}
        onSubmit={(e) => {
          e.preventDefaul()
          updateSearch(e)
        }}
      />
      {products.length > 0 ? (
        <Table products={products} />
      ) : (
        <div className="flex items-center justify-center pa5">
          <Spinner />
        </div>
      )}
    </Fragment>
  )
}

export default Main
