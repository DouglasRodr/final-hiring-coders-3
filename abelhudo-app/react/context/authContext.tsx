import React, { createContext, useState } from 'react'

export const AuthContext = createContext<AuthType | null>(null)

export type AuthType = {
  token: string | undefined
  updateToken: (data: string) => void
}

export const AuthStorage: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const [token, setToken] = useState<string>()

  const updateToken = (info) => {
    setToken(info)
  }

  return (
    <AuthContext.Provider value={{ token, updateToken }}>
      {children}
    </AuthContext.Provider>
  )
}
