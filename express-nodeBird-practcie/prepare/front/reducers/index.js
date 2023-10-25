import { HYDRATE } from "next-redux-wrapper";
import { combineReducers } from "redux";
import user from "./user";
import post from "./post";



//(이전상태,액션) => 다음상태
const rootReducer = combineReducers({

  index: (state = {}, action) => {
    switch (
      action.type //ction.type : CHANGE_NICKNAME
    ) {
      case HYDRATE:
        console.log("HYDRATE = ", HYDRATE);
        return { ...state, ...action.payload };

      default:
        return state;
    }
  },
  user,
  post,
});

export default rootReducer;
