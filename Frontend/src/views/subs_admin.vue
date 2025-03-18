<template>
  <div class="sub">
    <add_sub @add_subscription="add_subscription($event)" @accept="update_sub($event)"/>
    <sub_admin @update_sub="update_sub($event)" @delete_sub="delete_sub($event)"/>
  </div>
</template>
<script>
import sub_admin from "../components/subsc_admin.vue";
import add_sub from "../components/add_sub.vue";
import $ from 'jquery';
export default {
  name: "admin_sub",
  components: {
    sub_admin,
    add_sub,
  },
   data(){
     return{
       msg : "",
     }
   },
   async created(){
      this.$store.dispatch('getuser',this.$store.state.token )
        .then((reponse) => {
          this.$store.state.user = reponse.data
          this.$store.commit('refadminSubscription');
          this.$store.commit('refSuggestion'); 
          });
      this.$store.commit('refCity');
      this.$store.commit('refAutocar');
        
      
       
    },
  methods:{
    add_subscription :   function(subscription){
      this.$store.dispatch('add_subscription',subscription)
       .then(async (reponse) => {
         $('#add')
            .hide()
            .find("input")
            .val("")
            .end();
        alert(reponse.data);
        this.msg="";
        this.$store.commit('refadminSubscription');
        })
      .catch((errors) => {
        this.msg = errors.response.data;
      });

    },

    update_sub :  function(subscription){
      this.$store.dispatch('update_subscription',subscription)
      .then(async (response) => {
        alert(response.data);
        this.$store.commit('refadminSubscription');
        })
      .catch(async (errors) => {
          this.msg=errors.response.data;
        console.log(errors);
        this.$store.commit('refadminSubscription');
      });
    },
    delete_sub  :  function(id){
      this.$store.dispatch('delete_subscription',id)
      .then( async (reponse) => {
        alert(reponse.data);
        this.$store.commit('refadminSubscription');
        })
      .catch((errors) => {
        alert("try again, country not deleted ! !")
        console.log(errors)
      });
    },

  }
  
 
};
</script>
