

export const initualState = {
    isLoginIn: false,
    me: null,
    signUpDate: {},
    loginData: {},
}

//액션 객체
export const loginAction = (data) => {
    return {
      type: "LOGIN",
      data,
    };
  };
  export const logoutAction = (data) => {
    return {
      type: "LOGOUT",
      data,
    };
  };
  
  //액션 객체 끝

  //리듀서
const reducer = (state = initualState, action) =>{

     switch (action.type){
        case "LOGIN":
            return {
                ...state,
                isLoginIn: true,
                me: action.data,
            };
          case "LOGOUT":
            return {
                ...state,
                isLoginIn: false,
                me: null,
            };
        default:
            return state;
     }
};

export default reducer;