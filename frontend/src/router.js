import Vue from 'vue'
import Router from 'vue-router'

// Containers
const TheContainer = () => import('@/containers/TheContainer')

// Views
const Dashboard = () => import('@/views/Dashboard')

// Pages
const Login = () => import('@/views/pages/Login')
const Page404 = () => import('@/views/pages/Page404')
const Page500 = () => import('@/views/pages/Page500')
const Register = () => import('@/views/pages/Register')

// Users
const Users = () => import('@/views/Users')
const User = () => import('@/views/User')

//import Hello from '@/views/Hello'
//import Service from '@/views/Service'
//import Dashboard from '@/views/Dashboard'
//import Test from '@/views/Test'
//import User from '@/views/User'
//import Users from '@/views/Users'
//import Login from '@/views/Login'
//import Protected from '@/views/Protected'

import store from './store'

Vue.use(Router);

const router = new Router({
  mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
  routes: configRoutes()
});

function configRoutes() {
  return [
    {
      path: '/',
      redirect: '/dashboard',
      name: 'Home',
      component: TheContainer,
      meta: {
         requiresAuth: true
      },
      children: [
        {
          path: 'dashboard',
          name: 'Dashboard',
          component: Dashboard
        },
        {
          path: 'users',
          meta: {
            label: 'Users'
          },
          component: {
            render(c) {
              return c('router-view')
            }
          },
          children: [
            {
              path: '',
              name: 'Users',
              component: Users
            },
            {
              path: ':id',
              meta: {
                label: 'User Details'
              },
              name: 'User',
              component: User
            }
          ]
        }
      ]
    },
    {
      path: '/pages',
      redirect: '/pages/404',
      name: 'Pages',
      component: {
        render(c) { return c('router-view') }
      },
      children: [
        {
          path: '404',
          name: 'Page404',
          component: Page404
        },
        {
          path: '500',
          name: 'Page500',
          component: Page500
        },
        {
          path: 'login',
          name: 'Login',
          component: Login
        },
        {
          path: 'register',
          name: 'Register',
          component: Register
        }
      ]
    }, {
      path: '/login',
      name: 'Login',
      component: Login

    },


  ]


  // [
  //     { path: '/', component: Hello },
  //     { path: '/dashboard', component: Dashboard },
  //     { path: '/test', component: Test },
  //     { path: '/users', component: Users },
  //     { path: '/callservice', component: Service },
  //     { path: '/user', component: User },
  //     { path: '/login', component: Login },
  //     {
  //         path: '/protected',
  //         component: Protected,
  //         meta: {
  //             requiresAuth: true
  //         }
  //     },

  //     // otherwise redirect to home
  //     { path: '*', redirect: '/' }
  // ]
}

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (!store.getters.isLoggedIn) {
      next({
        path: '/login'
      })
    } else {
      next();
    }
  } else {
    next(); // make sure to always call next()!
  }
});

export default router;