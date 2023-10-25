import React from "react";
import Head from "next/head";
import AppLayout from "../components/AppLayout";
const admin = () => {
  return (

      <div>
        <AppLayout/>
            <Head>
                <title>관리자페이지 | 노드버드</title>
         </Head>
        <div> admin</div>
      </div>
   
  );
};

export default admin;
