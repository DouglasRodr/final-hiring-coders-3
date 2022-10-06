import React from 'react'
import { Layout, PageHeader, PageBlock } from 'vtex.styleguide'

import Main from './components/pages/Main'
import { AuthStorage } from './context/authContext'

const Abelhudo: React.FC = () => {
  return (
    <Layout fullWidth pageHeader={<PageHeader title="Abelhudo" />}>
      <PageBlock
        variation="full"
        title="Insights"
        subtitle="Texto fofinho sobre as funcionalidades"
      >
        <AuthStorage>
          <Main />
        </AuthStorage>
      </PageBlock>
    </Layout>
  )
}

export default Abelhudo
