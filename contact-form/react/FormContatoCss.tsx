import styled from 'styled-components';

export const ContainerForm = styled.div`
    background-color: #d9d9d9;
    height: 40vh;
    width: 100%; 
    display: flex;
    color: black;
    position: relative;
    border-radius: 5px;
    @media (max-width: 767px) {
        position: relative;
        height: 30vh;
        margin-left: 0;
        border-radius: 3px;
    }
    @media screen and (min-width:768px) and (max-width:1023px) {
        margin-top: 0;
        border-radius: 3px;
        height: 32vh;
    }
 
`
export const InputForm = styled.input`
    width:29vw;
    height:5vh;
    display:flex;
    align-items:center;
    padding-left: 25px;
    border: none;
    color:black;
    border-radius: 5px;
    border-color: transparent;
    @media (max-width: 767px) {
        width:75vw;
        height: 4vh;
        margin-top:-8px;
        margin-left: 0;
        border-radius: 3px;
    }
    @media screen and (min-width:768px) and (max-width:1023px) {
        margin-top: 0;
        border-radius: 3px;
        width: 40vw;
        height: 4.5vh;
    }
  
`

export const TextAreaForm = styled.textarea`
    width:29vw;
    height:10vh;
    display:flex;
    align-items:center;
    padding-top: 20px;
    padding-left: 25px;
    border: none;
    color:black;
    border-radius: 5px;
    border-color: transparent;
    resize: none;
    @media (max-width: 767px) {
        width:75vw;
        height: 8vh;
        padding-top: 10px;
        margin-top:-8px;
        margin-left: 0;
        border-radius: 3px;
    }
    @media screen and (min-width:768px) and (max-width:1023px) {
        margin-top: 0;
        border-radius: 3px;
        width: 40vw;
        height: 8vh;
    }
  
`
export const ButtonForm = styled.button.attrs({ type: 'submit' })`
    background-color: #ffc800;
    width:29vw;
    height:5vh;
    text-align:center;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    border-color: transparent;
    transition: 0.15s;
    text-align: center;
    &:hover{
        background-color: #f1ac15;
    }
    &:active {
        background-color: #f1ac15;
    }
    @media (max-width: 767px) {
        width:75vw;
        height: 4vh;
        padding-left: 0;
        margin-top:-8px;
        margin-left: 0;
        border-radius: 3px;
    }
    @media screen and (min-width:768px) and (max-width:1023px) {
        margin-top: 0;
        border-radius: 3px;
        width: 40vw;
        height: 4.5vh;
    }
 
`
export const FormContainer = styled.form `
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    @media (max-width: 767px) {
        margin-top: 0;
        border-radius: 3px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
    @media screen and (min-width:768px) and (max-width:1023px) {
        margin-top: 0;
        border-radius: 3px;
        position: absolute;
    }
`