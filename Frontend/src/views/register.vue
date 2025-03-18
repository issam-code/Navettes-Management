<template>
  <div class="register_page">
    <register :msg="mymsg" @add="add($event)" />
  </div>
</template>

<script>
import register from '../components/register_tem.vue';
export default {
  name: "register_page",
  components: {
    register
  },
  data () {
        return{
            mymsg : "",
        }
    },
  methods : {
    add : async function(user){
      if(user.password.length < 5){
          this.mymsg = "password most be > 4"; 
      }else{
        this.$store.dispatch('add_user',user)
          .then(() => {
            this.$router.push("/")
          })
          .catch( (error) => {
            this.mymsg = error.response.data;
          });      
      }
      
     
    }
  }
};
</script>