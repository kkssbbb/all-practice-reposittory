import React from "react";
import AppLayout from "../components/AppLayout";
import Head from 'next/head'
import NicnameEditForm from "../components/NicnameEditForm";
import FollowList from "../components/FollowList";
const proflie = () => {
  const followerList = [{ nickname: '제로초' }, { nickname: '바보' }, { nickname: '노드버드오피셜' }];
  const followingList = [{ nickname: '제로초' }, { nickname: '바보' }, { nickname: '노드버드오피셜' }];
  
  return (
    <div>
        
            <Head>
                <title>프로필 | 노드버드</title>
         </Head>
      <AppLayout >
      <NicnameEditForm></NicnameEditForm>
      <FollowList header="팔로잉 목록" data={followingList}></FollowList>
      <FollowList header="팔로잉 목록" data={followerList}></FollowList>
      </AppLayout>
    </div>
  );
};

export default proflie;
