<template>
  <div class="search_sub">
    <div class="col-md-10 container" >
      <form class="row bg-light" method="post" enctype="multipart/form-data" @submit.prevent="search()" style="margin: 40px; border: 1px solid grey; padding: 15px; border-radius: 10px;" >
        <div class="form-floating col-md-3">
          <!-- <select class="form-select" id="from"  aria-label="Floating label select example">
            <option selected="" disabled>Departure City</option>
            <option v-for="(city,i) in this.$store.state.city" :key="'C'+i" :value="city.name">{{city.name}}</option>
          </select> -->
          <select v-model="this.$store.state.city_depart"  class="form-select" id="from"  aria-label="Floating label select example" required>
            <option v-for="(item) in this.$store.state.city" 
                :value="item.name" 
                :key="item.id">
                  {{item.name}}
            </option>
          </select>
          <label class="mx-2" for="from">Departure City</label>
        </div>
        <div class="form-floating col-md-3">
          <select v-model="this.$store.state.city_arriv" class="form-select" id="to" aria-label="Floating label select example" required>
            <option v-for="(item) in this.$store.state.city" 
                :value="item.name" 
                :key="item.id">
                  {{item.name}}
            </option>
          </select>
          <label class="mx-2" for="to">Arrival City</label>
        </div>
        <div class="form-floating col-md-3">
          <input v-model="this.$store.state.date_depart" id="departure" class="form-control" type="date" required />
          <label class="mx-2" for="departure">Departure Date</label>
        </div>
        <div class="form-floating col-md-3">
          <div class="row">
            <button type="submit" class="btn btn-warning col-md-8" style="width : 60%;  height : 50px;  margin-top: 2%; text-align-last: center; border: 1px solid rgb(184, 163, 163); padding: 15px;" >Search</button>
            <img class="col-md-4 " src="../assets/refresh.png" alt="" v-on:click="refresh()" style="width:25%; margin-left: 20px; " >
          </div>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
export default {
  name: "search_subscription",
  data() {
    return{
       
    }
},
methods:{
   search : function(){
    this.$store.state.subscription = []; 
    this.$store.commit('refSearch');
     
  },
  refresh : function(){
    this.$store.state.city_depart = null; 
    this.$store.state.city_arriv = null;
    this.$store.state.date_depart = null;
    this.$store.commit('refSubscription');
     
  },

}
 
}
</script>
