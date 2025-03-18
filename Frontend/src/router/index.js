import { createRouter, createWebHistory } from "vue-router";
import store from "../store";
import Subscriptions from "../views/Subscriptions.vue";
import Login from "../views/login.vue";
import Register from "../views/register.vue";
import List from "../views/subs_admin.vue";

const routes = [
  {
    path: "/subscriptions",
    name: "Subscriptions",
    component: Subscriptions,
    beforeEnter: (toolbar, from, next) => {
      if(!store.state.token) {
        next("/");
      } else {
        next();
      }
    }
  },
  {
    path: "/",
    name: "Login",
    component: Login,
    beforeEnter: () => {
      store.state.token=null;
      
    }
  },
  {
    path: "/list",
    name: "List",
    component: List,
    beforeEnter: (toolbar, from, next) => {
      if(!store.state.token) {
        next("/");
      } else {
        next();
      }
    }
  },
  {
    path: "/",
    name: "Register",
    component: Register,
      
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
