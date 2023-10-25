import React from "react";
import AppLayout from "../components/AppLayout";
import { useSelector } from 'react-redux';

import PostForm from '../components/PostForm';
import PostCard from '../components/PostCard';

const Home = () => {
  const { isLoginIn } = useSelector(state => state.user);
  const { mainPosts } = useSelector(state => state.post);
console.log(isLoginIn);

  return (
    <AppLayout>
    {isLoginIn && <PostForm />}
    {mainPosts.map((c) => {
      return (
        <PostCard key={c.id} post={c} />
      );
    })}
  </AppLayout>
  );
}

export default Home;
