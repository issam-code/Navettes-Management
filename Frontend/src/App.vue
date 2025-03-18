<template>
  <div id="app">
    <div class="navbar navbar-light bg-dark col-md-12">
      <router-link class="col-md-3 col-3 text-left "  :to="{ name: 'Subscriptions' }" style=" color: white;  text-decoration:none; ">
        <div class="row">
          <img src="./assets/logo3.png" alt="" class="col-md-6 col-6" style=" width: 90px; margin-left: 10px;"  >
          <h3 class="col-md-6 col-6" style="padding-top: 15px; text-align: left; margin-left: -20px;">Navettes</h3>
        </div>
      </router-link>
      <div class="col-md-9 col-9 text-right ">
        <div class="row">
          <div class="col-md-8 col-8">

          </div>
          <div class="col-md-4 col-4">
            <div class="row">
              <router-link v-if="!$store.state.token" style="color: white;  text-decoration:none; "  class="col-md-6 col-6"  :to="{ name: 'Register' }"><h5 >Sign Up</h5></router-link>   
              <router-link v-if="!$store.state.token" style="color: white;  text-decoration:none; " class="col-md-6 col-6" :to="{ name: 'Login' }"><h5 >Sign In</h5></router-link>
            </div>
          </div>
               
        </div>
        <div class="row">
          <a href=""  class=" text-right col-md-4 col-4"   style="color: white;  text-decoration:none; "><h5 v-if="$store.state.token" @click="this.$router.push('Subscriptions')"> <i class="bi bi-house-door" style="margin-right : 2px"></i> Home</h5></a>
          <a href=""  class=" text-right col-md-4 col-4"   style="color: white;  text-decoration:none; "><h5 v-if="$store.state.token && this.$store.state.user.role == 'ADMIN' " @click="this.$router.push('list')"> <i class="bi bi-gear" style="margin-right : 2px"></i> Manage</h5></a>    
          <a href=""  class=" text-right col-md-4 col-4"   style="color: white;  text-decoration:none; "><h5 v-if="$store.state.token" @click="$store.commit('logout')"><i class="bi bi-box-arrow-left" style="margin-right : 10px"></i>Log out</h5></a>
        </div>
             
      </div>
    </div>
    <router-view />
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
