<template>
  <div class="sub">
    <card_top />
    <search_subscription @book="book($event)" />
    <subscriptions_tem :msg="msg" @book="book($event)" @request="request($event)"/>
  </div>
</template>
<script>
import subscriptions_tem from "../components/Subscriptions_tem.vue";
import card_top from "../components/card_top.vue";
import search_subscription from "../components/Search_bar.vue";

export default {
  name: "all_Subscriptions",
  components: {
    subscriptions_tem,
    card_top,
    search_subscription,
  },
  
  data() {
    return {
      msg:""
    };
  },
  async created () {
      this.$store.commit('refSubscription');
  },
  methods: {
    book :  function(bookn){
      console.log(bookn);
      this.$store.dispatch('booking',bookn)
      .then(async (response) => {
        alert(response.data);
        this.$store.commit('refSubscription');
        })
      .catch(async (errors) => {
          this.msg=errors.response.data;
        console.log(errors);
        this.$store.commit('refSubscription');
      });
    },
     request :   function(subscription){
      this.$store.dispatch('suggest',subscription)
       .then(async (reponse) => {
        
        alert(reponse.data);
        this.msg="";this.$store.state.city_depart = null; 
        this.$store.state.city_arriv = null;
        this.$store.state.date_depart = null;
        this.$store.commit('refSubscription');
        })
      .catch((errors) => {
        this.msg = errors.response.data;
      });

    },
    
  },
};
</script>
