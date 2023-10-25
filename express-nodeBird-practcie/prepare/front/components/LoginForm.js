import React, { useCallback } from "react";
import { Button, Checkbox, Form, Input } from "antd";
import Link from "next/link";
import styled from "styled-components";
import useInput from "/Users/mac/Desktop/DV/react-nodebird-master/prepare/front/hooks/useInput.js";
import { useDispatch } from "react-redux";
import { loginAction } from "../reducers/user";

const FormWrapper = styled(Form)`
  margin-top: 50px;
`;

const LoginForm = () => {
  const dispacth = useDispatch();
  const [id, onChangeId] = useInput("");
  const [password, onChangePassword] = useInput("");

  const onFinishForm = useCallback(() => {
    console.log(id, password);
    dispacth(loginAction({id, password}));
  }, [id, password]);

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  return (
    <FormWrapper
      name="basic"
      labelCol={{
        span: 8,
      }}
      wrapperCol={{
        span: 16,
      }}
      initialValues={{
        remember: true,
      }}
      onFinish={onFinishForm}
      onFinishFailed={onFinishFailed}
      autoComplete="off"
    >
      <Form.Item
        label="이메일"
        name="username"
        rules={[
          {
            required: true,
            message: "Please input your username!",
            name: "Username",
            value: id,
            onChangeId: onChangeId,
          },
        ]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        label="비밀번호"
        name="password"
        rules={[
          {
            required: true,
            message: "Please input your password!",
            name: "Username",
            value: password,
            onChangeId: onChangePassword,
          },
        ]}
      >
        <Input.Password />
      </Form.Item>

      <Form.Item
        name="remember"
        valuePropName="checked"
        wrapperCol={{
          offset: 8,
          span: 16,
        }}
      >
        <Checkbox>Remember me</Checkbox>
      </Form.Item>

      <Form.Item
        wrapperCol={{
          offset: 8,
          span: 16,
        }}
      >
        <Button type="primary" htmlType="submit">
          로그인
        </Button>
        <Link href={"/singup"}>
          <a>
            <Button>회원가입</Button>
          </a>
        </Link>
      </Form.Item>
    </FormWrapper>
  );
};

export default LoginForm;
