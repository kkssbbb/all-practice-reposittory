import React, {useCallback} from "react";
import { Card,Avatar,Button } from "antd";
import { useDispatch } from "react-redux";
import {logoutAction} from '../reducers/user'

const UserProflie = () => {
    
    const dispacth = useDispatch();

    const onLogOut = useCallback(()=>{
        dispacth(logoutAction());    
    },[]);
  
    return (
    <Card
        actions={[
            <div key="twit">짹짹<br/> 0</div>,
            <div key="followings">팔로워<br /> 0</div>,
            <div key="forllwings">팔로워<br /> 0</div>,
        ]}
        >
        <Card.Meta
        avatar={<Avatar>ZC</Avatar>}
        />
        <Button onClick={onLogOut}>로그아웃</Button>
    </Card>
  );
};

export default UserProflie;
