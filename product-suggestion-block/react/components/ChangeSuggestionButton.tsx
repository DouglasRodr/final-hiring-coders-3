import React from 'react'
import { ButtonWithIcon } from 'vtex.styleguide'
import { useCssHandles } from 'vtex.css-handles'

import IconRefresh from '../icons/IconRefresh'

const CSS_HANDLES = [
  'changeSuggestionButton',
]

interface Props {
  hideChangeAction?: boolean
  index: number
  onChangeProduct: (index: number) => void
}

export default function ChangeSuggestionButton({ hideChangeAction, index, onChangeProduct }: Props) {

  const handles = useCssHandles(CSS_HANDLES)

  if (hideChangeAction) {
    return null;
  }

  return (
    <div className={`${handles.changeSuggestionButton}`}>
      <ButtonWithIcon
        icon={<IconRefresh />}
        onClick={() => onChangeProduct(index)}
      >
        Trocar
      </ButtonWithIcon>
    </div>
  )
}
