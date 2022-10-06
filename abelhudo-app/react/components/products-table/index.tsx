import React, { Fragment, useState } from 'react'
import { Button, Table, Modal } from 'vtex.styleguide'

import ProductData from '../product-modal'

export interface ProductType {
  id: number
  name: string
  active: boolean
  quantitySold: number
  image?: string
  price?: string
}

const ProductsTable: React.FC<{ products: ProductType[] }> = ({ products }) => {
  const [mainProduct, setMainProduct] = useState<ProductType>()
  const [isModalOpen, setIsModalOpen] = useState(false)

  const toggleModal = async ({ rowData }) => {
    const itemInfo = await fetch(
      `/api/catalog_system/pub/products/variations/${rowData.id}`
    )

    const { skus } = await itemInfo.json()

    setMainProduct({
      ...rowData,
      image: skus[0].image,
      price: skus[0].bestPriceFormated,
    })
    setIsModalOpen(!isModalOpen)
  }

  const defaultSchema = {
    properties: {
      id: {
        title: 'ID',
        width: 50,
      },
      name: {
        title: 'Produto',
        minWidth: 400,
      },
      quantitySold: {
        title: 'Und. Vendidas',
        width: 120,
      },
      action: {
        title: 'Ver Produto',
        // eslint-disable-next-line react/display-name
        cellRenderer: (rowData) => {
          return (
            <Button
              onClick={() => {
                toggleModal({ ...rowData })
              }}
            >
              Ver Produto
            </Button>
          )
        },
      },
    },
  }

  return (
    <Fragment>
      <Table fixFirstColumn fullWidth schema={defaultSchema} items={products} />
      {mainProduct && (
        <Modal
          centered
          isOpen={isModalOpen}
          onClose={() => {
            setIsModalOpen(false)
          }}
        >
          <ProductData product={mainProduct} />
        </Modal>
      )}
    </Fragment>
  )
}

export default ProductsTable
