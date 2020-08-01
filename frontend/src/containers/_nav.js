export default [
  {
    _name: 'CSidebarNav',
    _children: [
      {
        _name: 'CSidebarNavItem',
        name: 'Dashboard',
        to: '/dashboard',
        icon: 'cil-speedometer',
        badge: {
          color: 'primary',
          text: 'NEW'
        }
      },
      {
        _name: 'CSidebarNavTitle',
        _children: ['Administration']
      },
      {
        _name: 'CSidebarNavItem',
        name: 'Client',
        to: '/client',
        icon: 'cil-star'
      },
      {
        _name: 'CSidebarNavTitle',
        _children: ['Lists']
      },
      {
        _name: 'CSidebarNavItem',
        name: 'Users',
        to: '/users',
        icon: 'cil-user',
        badge: {
          color: 'primary',
          text: 'NEW',
          shape: 'pill'
        }
      },
      {
        _name: 'CSidebarNavItem',
        name: 'Hello',
        to: '/hello',
        icon: 'cil-calculator'
      },
      {
        _name: 'CSidebarNavDivider',
        _class: 'm-2'
      },
      {
        _name: 'CSidebarNavTitle',
        _children: ['Extras']
      },
      {
        _name: 'CSidebarNavDropdown',
        name: 'Pages',
        route: '/pages',
        icon: 'cil-star',
        items: [
          {
            name: 'Login',
            to: '/login'
          },
          {
            name: 'Register',
            to: '/register'
          },
          {
            name: 'Error 404',
            to: '/404'
          },
          {
            name: 'Error 500',
            to: '/500'
          }
        ]
      }
    ]
  }
]