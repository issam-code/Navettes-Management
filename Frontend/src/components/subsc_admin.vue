<template>
    <div class="subscriptions_tem">
        <div class="col-md-10 col-10 container" >           
            <div class="col-sm-12 table-responsive">
                <table id="example1" class="table table-bordered table-striped dataTable">
                    <thead>
                        <tr role="row">
                            <th>#</th>
                            <th>Depart City</th>
                            <th>Arrival City</th>
                            <th>Depart Time</th>
                            <th>Arrival Time</th>
                            <th>Depart Date</th>
                            <th>Nb Required</th>
                            <th>Autocar</th>
                            <th>Price (DH)</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(p,i) in this.$store.state.subscription" :key="'C'+i" >
                            <td >{{i+1}}</td>
                            <td >{{p.city_depart}}</td>
                            <td>{{p.city_arriv}}</td>
                            <td>{{p.time_depart}}</td>
                            <td>{{p.time_arriv}}</td>
                            <td>{{p.date_depart}}</td>
                            <td>{{p.n_wanted}}</td>
                            <td>{{p.autocar.name}}</td>
                            <td>{{p.price}}</td>
                            <td>
                                <i class="bi bi-pencil-square" style="cursor: pointer; margin-right : 20px" @click="get_sub(p)" data-bs-toggle="modal" data-bs-target="#update_subscription"></i>
                                <i class="bi bi-trash" style="cursor: pointer;"  @click="delete_sub(p.id)"></i>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <pagination v-model="page" :records="this.$store.state.size" :per-page="this.$store.state.limit" @paginate="change"/>
            </div>
        </div>

        <div class="modal fade" id="update_subscription" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form  class="frmm" method="post" enctype="multipart/form-data" @submit.prevent="update_subscription()">
                        <div class="modal-header bg-light">						
                            <h4 class="modal-title">Update Subscription</h4>
                        </div>
                        <div class="modal-body">
                                <div class="row">
                                    <div class=" form-group col-md-6">
                                        <label></label>
                                        <select v-model="subscription.city_depart" class="form-select" id="to" aria-label="Floating label select example" required>
                                            <option value="" disabled>Depart City</option>
                                            <option 
                                                v-for="(item) in this.$store.state.city" 
                                                :value="item.name" 
                                                :key="item.id">
                                                {{item.name}}
                                            </option>
                                        </select>
                                    </div>
                                    <div class=" form-group col-md-6">
                                        <label></label>
                                        <select v-model="subscription.city_arriv" class="form-select" id="to" aria-label="Floating label select example" required>
                                            <option value="" disabled>Arrival City</option>
                                            <option 
                                                v-for="(item) in this.$store.state.city" 
                                                :value="item.name" 
                                                :key="item.id">
                                                {{item.name}}
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class=" form-group col-md-6">
                                        <label></label>
                                        <input v-model="subscription.time_depart" type="text" class="form-control" onfocus="type='time'" placeholder="Depart Time" required>
                                    </div>
                                    <div class=" form-group col-md-6">
                                        <label></label>
                                        <input v-model="subscription.time_arriv" type="text" class="form-control" onfocus="type='time'" placeholder="Arrival Time" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class=" form-group col-md-6">
                                        <label></label>
                                        <input v-model="subscription.date_depart" id="departure" class="form-control" type="text" onfocus="type='date'" placeholder="Depart Date" required>
                                    </div>
                                    <div class=" form-group col-md-6">
                                        <label></label>
                                        <input v-model="subscription.n_wanted" type="number" class="form-control" placeholder="Number Places Required" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class=" form-group col-md-6">
                                        <label></label>
                                        <select v-model="subscription.id_autocar" class="form-select" id="to" aria-label="Floating label select example" required>
                                            <option :value="subscription.id_autocar">{{autocar}}</option>
                                            <option 
                                                v-for="(item) in this.$store.state.autocar" 
                                                :value="item.id" 
                                                :key="item.id">
                                                {{item.name}}
                                            </option>
                                        </select>
                                    </div>
                                    <div class=" form-group col-md-6">
                                        <label></label>
                                        <input v-model="subscription.price" type="number" class="form-control" placeholder="Price" required>
                                    </div>
                                </div>
                                <!-- <span  class="text-center text-danger"><p >{{name}}</p> </span> -->
                            
                        </div>
                        <div class="modal-footer bg-light">
                            <input type="button" class="btn btn-secondary" data-bs-dismiss="modal" value="Cancel">
                            <button type="submit"  class="btn btn-warning ">Update</button>                    
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import Pagination from 'v-pagination-3';

export default {
  name: "subscriptions_tem",
    data(){
    return{
        page: 1,
        selected: 1,
        subscription : {
            city_depart : "",
            city_arriv : "",
            time_depart : "",
            time_arriv : "",
            date_depart : "",
            n_wanted : "",
            id_autocar : "",
            price : "",
            id_user : "",
            type : ""
        },
        id:"",
        autocar:"",
    }
    },
    components: {
      Pagination
    },

    methods: {
        change(value){
           this.page = value;
            this.$store.state.page = this.page -1 ;
            this.$store.commit('refadminSubscription');    
        }, 
        get_sub(sub){
            this.subscription = {
                city_depart : sub.city_depart,
                city_arriv : sub.city_arriv,
                time_depart : sub.time_depart,
                time_arriv : sub.time_arriv,
                date_depart : sub.date_depart,
                n_wanted : sub.n_wanted,
                id_autocar : sub.autocar.id,
                price : sub.price,
                id_user : this.$store.state.user.id,
                type : this.$store.state.user.role
            };
            this.id=sub.id;
            this.autocar=sub.autocar.name
        },
        delete_sub : function(id) {
            var result = confirm("Want to delete?");
            if (result) {
                // alert("ok"+id)
                this.$emit('delete_sub',id);
            }
        },
        update_subscription : function(){
            this.$emit('update_sub',{"id":this.id,"sub":this.subscription});
            // console.log({"id":this.id,"sub":this.subscription})
            
        },
    },
  
}
</script>

