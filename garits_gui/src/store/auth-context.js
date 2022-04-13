import { createContext, useEffect, useState } from "react";

const AuthContext = createContext({
  authData: { idUser: null, token: null, roles: [], type: null, email: null },
  onLogin: () => {},
  onLogout: () => {},
  //onLogout: logoutHandler
});

export const AuthContextProvider = (props) => {
  const [authData, setAuthData] = useState({
    idUser: null,
    token: null,
    roles: [],
    type: null,
    email: null,
  });
  const onLogout = () => {
    setAuthData({
      idUser: null,
      token: null,
      role: null,
      type: null,
      email: null,
    });
    localStorage.removeItem("userAuthDetails");
  };
  const onLogin = (userAuthDetails) => {
    const newDetails = {
      idUser: userAuthDetails.idUser,
      token: userAuthDetails.token,
      role: userAuthDetails.roles[0],
      type: userAuthDetails.type,
      email: userAuthDetails.email,
    };
    localStorage.setItem("userAuthDetails", JSON.stringify(newDetails));

    setAuthData(newDetails);
  };
  useEffect(()=>{
    const userAuthDetails = JSON.parse(localStorage.getItem("userAuthDetails"));
    if(userAuthDetails){
      setAuthData(userAuthDetails);
    }
  },[]);
  return (
    <AuthContext.Provider value={{ authData, onLogout, onLogin }}>
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
