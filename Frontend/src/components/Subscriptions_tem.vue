<template>
  <div class="subscriptions_tem">

  <div class="col-md-9 container" >
    <div class="row" >
        <div class="card col-md-4 container text-start" v-if="this.$store.state.subscription.length == 0">
          <div class="card-header ">
            <p><strong><i class="bi bi-calendar4" style="margin-right: 5px;"></i> No trips on: {{this.$store.state.date_depart}}</strong></p>
          </div>
          <div v-if="this.$store.state.user.role == 'USER'" class="card-content" style="padding: 10px;">
            <p style="font-family: Courier; font-size: medium">Requesting this route will notify us that this route is of high priority to you and we will do our best to make this route available on the platform!</p>
            <button v-on:click="request()" class="btn btn-secondary col-6 " ><i class="bi bi-bus-front-fill"></i> Request Route</button>
          </div>
          
        </div>
        <div v-for="(p,i) in this.$store.state.subscription" :key="'C'+i" class="card bg-light mb-3 col-md-8 container" style=" ">
          <div class="row" >
            <div class="col-md-9">
              
              <div class="row" style="margin-top: 5px;" >
                 <p class="col-md-8 bg-warning" style="border-radius: 10px;text-align: left; margin-left: 15px;">{{p.user.description}}</p>
                <p v-if="p.autocar.air_con" class="col-md-2 bg-info" style="border-radius: 10px; "><i class="bi bi-snow3" style="margin-right: 12px;"> Clim</i></p>
              </div>
               
                <!-- <div class-="bg-warning" style="border-radius: 10px;"><p style=" text-align: left; margin-left: 15px;">Itrane Voyage - أسفار إثران</p></div> -->
              <div class="row" >
                <img src="../assets/logo1.png" class="col-md-3" style="" alt="">
                <div class="col-md-3  mt-4">
                  <h3>{{p.time_depart}}</h3>
                  <h6><strong>{{p.city_depart}}</strong></h6>
                  <!-- <p>Ouled zian</p> -->
                </div>
                <div class="col-md-3">
                  <h5 class="card-title" style="font-size: 80px; "><p>&#8594;</p></h5>
                </div>
                <div class="col-md-3 mt-4">
                  <h3>{{p.time_arriv}}</h3>
                  <h6><strong>{{p.city_arriv}}</strong> </h6>
                  <!-- <p>El Massira</p> -->
                </div>
              </div>
             
            </div>
            <!-- <div class="col-md-1  " ><div class="vl"></div></div> -->
            <div class="col-md-3" style="border-left-style: ridge;     text-align-last: left;">
              <div class="card-body">
                <h3 style="margin-bottom: 0px;"> <strong class="text-warning"> {{p.price}} DH</strong> </h3>
                <p class="text-secondary">per person</p>
                <div >
                  <p v-if="(p.n_reached >= p.n_wanted)" class=" bg-danger"  style="color: white; width : 100%; margin-top: 20%; text-align-last: center; border-radius: 10px; ">Full</p>
                  <button v-else type="button" class="btn btn-warning " style="width : 100%; margin-top: 20%; text-align-last: center;" @click="get_sub(p.id)" data-bs-toggle="modal" data-bs-target="#book">Select</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      

    </div>
     <!-- <b-pagination
                style="place-content: center;"                
                v-model="page"
                :total-rows=" this.$store.state.size" 
                :per-page="this.$store.state.limit"
                @change="change"
      /> -->
      
      <div>

      <pagination v-model="page" :records="this.$store.state.size" :per-page="this.$store.state.limit" @paginate="change"/>

      </div>

    <div class="modal fade" id="book" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form  class="frmm" method="post" enctype="multipart/form-data" @submit.prevent="book()">
                        <div class="modal-header bg-light">						
                            <h4 class="modal-title">Your Booking</h4>
                        </div>
                        <div class="modal-body">
                                
                                <div class="row">
                                    <div class=" form-group col-md-6">
                                        <label></label>
                                        <input v-model="bookn.name" type="text" class="form-control"  placeholder="Name" required>
                                    </div>
                                    <div class=" form-group col-md-6">
                                        <label></label>
                                        <input v-model="bookn.telephone" type="text" class="form-control"  placeholder="Phone" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class=" form-group col-md-6">
                                        <label></label>
                                        <input v-model="bookn.nb_places"  class="form-control" type="number"  placeholder="Places Number" required>
                                    </div>
                                    
                                </div>
                                
                                <span  class="text-center text-danger"><p >{{msg}}</p> </span>
                            
                        </div>
                        <div class="modal-footer bg-light">
                            <input type="button" class="btn btn-secondary" data-bs-dismiss="modal" value="Cancel">
                            <button type="submit"   class="btn btn-warning ">Book</button>                    
                        </div>
                    </form>
                </div>
            </div>
        </div>
  </div>
    
            

  </div>
</template>
<script>
import Pagination from 'v-pagination-3';
// import axios from 'axios'
export default {
  name: "subscriptions_tem",
  props : ["msg"],
  data(){
    return{
      
      page: 1,
      selected: 1,
      bookn :{
        name :"",
        telephone : "",
        nb_places : "",
        id_subcription : "",
        id_user : "",
      }
    }
    },
    components: {
      Pagination
    },
    mounted(){
      

    },
  methods: {
    get_sub(id){
      this.bookn.id_user = this.$store.state.user.id
      this.bookn.id_subcription=id;
    },
    change(value){
           this.page = value;
            this.$store.state.page = this.page -1 ;
            if(this.$store.state.city_depart == null || this.$store.state.city_arriv==null || this.$store.state.date_depart==null ){
              this.$store.commit('refSubscription');
            }else{
              this.$store.commit('refSearch');
            }
            
            
       },
       
       book : function(){

        this.$emit('book',this.bookn);
        this.bookn = {
          name :"",
          telephone : "",
          nb_places : "",
          id_subcription : "",
          id_user : "",
        }
            
        },
        request : function(){
          const subscription = {
                city_depart : this.$store.state.city_depart,
                city_arriv : this.$store.state.city_arriv,
                time_depart : "0",
                time_arriv : "0",
                date_depart : this.$store.state.date_depart,
                n_wanted : 1,
                id_autocar : 1,
                price : "0",
                id_user : this.$store.state.user.id,
                type : this.$store.state.user.role
          };

          this.$emit('request',subscription);
        }
    
  },
};
</script>

<style>
.vl {
  border-left: 3px dotted grey;
  height: 70%;
  margin-top: 70%;
}
.pagination {
  display: flex;
  justify-content: center;
}
p.VuePagination__count {  display: none;
}</style>