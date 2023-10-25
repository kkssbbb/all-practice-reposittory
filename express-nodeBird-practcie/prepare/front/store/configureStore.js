//넥스트 리덕스 레퍼 라이브라리를 사용해서 넥스트에서 리덕스를 감편하게 사용할 수 있다.
import { createWrapper } from "next-redux-wrapper";
import { applyMiddleware, compose, createStore, } from "redux";
import {composeWithDevTools} from 'redux-devtools-extension'
import reducer from "../reducers";

const configureStore = (context) => {
  const middlewares = [];
  console.log(context);
  const enhancer = process.env.NODE_ENV === 'production'
  ? compose(applyMiddleware(...middlewares))
  : composeWithDevTools(applyMiddleware(...middlewares))

  const store = createStore(reducer, enhancer);
  return store;
};

//두번째 인자로 옵젼을 넣어 디버그 트루를 넣으면 리덕스에대해서 좀더 자세하게 나와서 개발할때는 디버그를
//트루로 넣는게 편하다.
const wrapper = createWrapper(configureStore, {
  debug: process.env.NODE_ENV === "development",
});

export default wrapper;
