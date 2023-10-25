import React  from "react";
import Link from "next/link";
import PropTypes from "prop-types";
import { Menu, Input, Row, Col } from "antd";
import UserProflie from "./UserProflie";
import LoginForm from "./LoginForm";
import styled from 'styled-components'
import {useSelector} from 'react-redux'

const InputSearch = styled(Input.Search)`
    vertical-align: middle
    `;

const AppLayout = ({ children }) => {
  
  
//isLoggedIn 값이 바뀌면 AppLayout이 알아서 리랜더링 된다
  const isLoggedIn = useSelector((state) => 
     state.user.isLoginIn
  )

  return (
    <div>
      <Menu mode="horizontal">
        <Menu.Item>
          <Link href="/">
            <a href="">노드 버드</a>
          </Link>
        </Menu.Item>
        <Menu.Item>
          <Link href="/proflie">
            <a href="">프로필</a>
          </Link>
        </Menu.Item>
        <Menu.Item>
          <Link href="/singup">
            <a href="">회원가입</a>
          </Link>
        </Menu.Item>
        <Menu.Item>
          <Link href="/admin">
            <a href="">관리자페이지</a>
          </Link>
        </Menu.Item>
        <Menu.Item>
          <InputSearch/>
        </Menu.Item>
      </Menu>
      <Row>
        <Col xs={24} md={6}>
          {isLoggedIn ? <UserProflie /> : <LoginForm/>}
        </Col>
        <Col xs={24} md={6}>
        {children}
        </Col>
        <Col xs={24} md={6}>
          <a href="https://www.naver.com" target="_black" rel="noreferrer nooperner"></a>
        </Col>
      </Row>

    </div>
  );
};

AppLayout.protoTypes = {
  children: PropTypes.node.isRequired,
};
export default AppLayout;
