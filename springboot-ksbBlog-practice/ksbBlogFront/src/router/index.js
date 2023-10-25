import Vue from "vue";
import Router from "vue-router";

const routerOptions = [
  { path: "/", component: "Landing" },
  { path: "/signin", component: "SignIn" },
  { path: "/signup", component: "SignUp" },
  { path: "/home", component: "Home", meta: { requiresAuth: true } },
  { path: "*", component: "NotFound" },
];

const routes = routerOptions.map((route) => {
  return {
    ...route,
    component: () => import(`../components/${route.component}.vue`),
  };
});

Vue.use(Router);

export default new Router({
  mode: "history",
  routes,
});
