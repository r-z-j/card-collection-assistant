import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import MTGView from '../views/MTGView.vue'
import PokemonTCGView from '../views/PokemonTCGView.vue'
import MTGSearchView from '../views/MTGSearchView.vue'
import PokemonTest from '../views/PokemonTest.vue'
import MyCollections from '../views/MyCollections.vue'
import AddMagicCard from '../views/AddMagicCard.vue'
import AddPokeCard from '../views/AddPokeCard.vue'
import ViewMTGCollectionView from '../views/ViewMTGCollectionView.vue'
import ViewPTCGCollectionView from '../views/ViewPTCGCollectionView.vue'
import AllCollections from '../views/AllCollections.vue'
import CreateCollection from '../views/CreateCollection.vue'
import FavoritedCollection from '../views/FavoritedCollection.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/mtg-view",
      name: "mtg-view",
      component: MTGView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/pokemon-tcg-view",
      name: "pokemon-tcg-view",
      component: PokemonTCGView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/add-magic_:id",
      name: "add-magic",
      component: AddMagicCard,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/add-poke_:id",
      name: "add-poke",
      component: AddPokeCard,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/create-collection",
      name: "create-collection",
      component: CreateCollection,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/collections",
      name: "collections",
      component: MyCollections,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/mtg-search",
      name: "mtg-search-view",
      component: MTGSearchView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/pokemon-search",
      name: "pokemon-search",
      component: PokemonTest,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/ptcg-collection_:id",
      name: "ptcg-collection-cards",
      component: ViewPTCGCollectionView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/mtg-collection_:id",
      name: "mtg-collection-cards",
      component: ViewMTGCollectionView,
      meta: {
        requiresAuth: false
      }
    },
      {
        path: "/allCollections",
        name: "all-collections",
        component: AllCollections,
        meta: {
          requiresAuth: false
        }

      },

      {
      path: "/favorited",
      name: "favorited",
      component: FavoritedCollection,
      meta: {
        requiresAuth: true
      }
    },
    
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
