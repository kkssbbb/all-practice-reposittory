import React, { useState, useCallback } from "react";
import AppLayout from "../components/AppLayout";
import Head from "next/head";
import { Checkbox, Form, Input, Button } from "antd";
import useInput from "/Users/mac/Desktop/DV/react-nodebird-master/prepare/front/hooks/useInput.js";
import styled from "styled-components";

const ErrorMessage = styled.div`
  color: red;
`;

const Singup = () => {
  //아이디 훅
  const [id, onChangeId] = useInput();
  const [password, onChangePassword] = useInput();
  const [nickname, onChangeNickname] = useInput();
  //비번 훅
  const [passwordErr, setPasswordErr] = useState(false);
  const [passwordCheck, setPasswordCheck] = useState("");
  const onChangePasswordCheck = useCallback(
    (e) => {
      setPasswordCheck(e.target.value);
      setPasswordErr(e.target.value !== password);
    },
    [password]
  );
  //체크박스 훅
  const [term, setTerm] = useState("");
  const [termError, setTermError] = useState("");
  const onChangeTerm = useCallback((e) => {
    setTerm(e.target.checked);
    setTermError(false);
  }, []);

  const onSubmit = useCallback(() => {
    if (password !== passwordCheck) {
      return setPasswordErr(true);
    }
    if (!term) {
      return setTermError(true);
    }
    console.log("서버로잘 보내지나 체크 콘솔 :" +id,nickname,password);
    
  }, [password, passwordCheck, term]);

  return (
    <AppLayout>
      <Head>
        <title>로그인 | 노드버드</title>
      </Head>
      <Form onFinish={onSubmit}>
        <div>
          <label htmlFor="user-id">아이디</label>
          <br />
          <Input name="user-id" value={id} required onChange={onChangeId} />
        </div>
        <div>
          <label htmlFor="user-nick">닉네임</label>
          <br />
          <Input
            name="user-nick"
            value={nickname}
            required
            onChange={onChangeNickname}
          />
        </div>
        <div>
          <label htmlFor="user-password">비밀번호</label>
          <br />
          <Input
            name="user-password"
            type="password"
            value={password}
            required
            onChange={onChangePassword}
          />
        </div>
        <div>
          <label htmlFor="user-password-check">비밀번호체크</label>
        </div>
        <div>
          <Input
            name="user-password-check"
            type="password"
            value={passwordCheck}
            required
            onChange={onChangePasswordCheck}
          />
          {passwordErr && (
            <ErrorMessage>비밀번호가 일치하지 않습니다.</ErrorMessage>
          )}
        </div>
        <div>
          <Checkbox name="user-term" checked={term} onChange={onChangeTerm}>
            동의???어보감
          </Checkbox>
          {termError && (
            <dErrorMessage style={{ color: "red" }}>
              동의 해야만 해
            </dErrorMessage>
          )}
        </div>
        <div>
          <Button type="primary" htmlType="submit">
            회원가입
          </Button>
        </div>
      </Form>
    </AppLayout>
  );
};

export default Singup;
