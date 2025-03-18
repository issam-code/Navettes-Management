<template>
  <div class="home">
    <Login :mymsg="msg" @verify="verify($event)" />
  </div>
</template>

<script>
import Login from '../components/login_tem.vue';
export default {
  name: "login_page",
  components: {
    Login
  },
  data(){
    return{
      msg : "",
    }
  },
  methods : {
    verify : async function(user){
      this.$store.dispatch('login',user)
      .then((reponse) => {
          localStorage.setItem("token", reponse.data);
          this.$store.state.token = reponse.data;
          this.$router.push("Subscriptions");

        })
        .catch((error) => {
          this.msg = "Username or Password Incorrect";
          console.log(error);
        }); 
     
    }
  }
};
</script>