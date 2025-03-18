<template>
    <div class="add_sub">
        <div class="col-md-10 col-10 container" >
            <div class="row bg-light" style="margin: 40px; border: 1px solid grey; padding: 15px; border-radius: 10px;" >
                <div class="col-4">
			        <h2>Manage <b>Subscriptions</b></h2>
				</div>
				
                <div class="col-6">
                   	<button type="button" class="btn btn-info float-end " data-bs-toggle="modal" data-bs-target="#suggest" style="color: white;  "><i class="bi bi-file-earmark-text" style="margin-right : 2px"></i>  Suggestions </button>
                </div>
                <div class="col-2">
					<button type="button" class="btn btn-warning float-end " data-bs-toggle="modal" data-bs-target="#add" style=""><i class="bi bi-plus-square" style="margin-right : 2px"></i>  Add New  </button>
				</div>
            </div>
        </div>


        <div class="modal" id="add"  >
            <div class="modal-dialog">
                <div class="modal-content">
                    <form  class="frmm" method="post" enctype="multipart/form-data" @submit.prevent="add()">
                        <div class="modal-header bg-light">						
                            <h4 class="modal-title">Add Subscription</h4>
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
                                            <option value="" disabled>Autocar</option>
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
                            <button type="submit" class="btn btn-warning ">Add</button>                    
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="modal fade" id="suggest" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog"  >
                <div class="modal-content" style=" margin-top: 20px; width:  150%; height: 450px;">
                    <div class="modal-body" >
                        <div class="text-center">						
                            <h4  class="bg-light" style="    padding: 15px; border-radius: 30px;"><b>Route Suggestions</b> </h4>
                        </div>
                            <div class="col-md-12 col-12 container" style="margin-top: 20px;">           
                                <div class=" table-responsive">
                                    <table id="example1" class="table table-bordered table-striped dataTable">
                                        <thead>
                                            <tr role="row">
                                                <th>#</th>
                                                <th>Depart City</th>
                                                <th>Arrival City</th>
                                                <th>Depart Date</th>
                                                <th>Nb traveler</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr v-for="(p,i) in this.$store.state.suggest.tab" :key="'C'+i" >
                                                <td >{{i+1}}</td>
                                                <td >{{p.city_depart}}</td>
                                                <td>{{p.city_arriv}}</td>
                                                <td>{{p.date_depart}}</td>
                                                <td>{{p.n_wanted}}</td>
                                                <td>
                                                    <i class="bi bi-check2-square" style="cursor: pointer;"  @click="accept(p)" data-bs-toggle="modal" data-bs-target="#update_subscription"></i>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <pagination v-model="page" :records="this.$store.state.suggest.size" :per-page="this.$store.state.suggest.limit" @paginate="change"/>
                                </div>
                            </div>   
                        </div>
                        
                </div>
            </div>
        </div>



    </div>
</template>


<script>
import Pagination from 'v-pagination-3';

export default {
  name: "add_subscription",
  data(){
        return{
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
           
            page:1,
            
        }
    },
     components: {
      Pagination
    },
    methods : {
        add : function(){
            this.subscription.id_user=this.$store.state.user.id;
            this.subscription.type=this.$store.state.user.role;
            this.$emit('add_subscription',this.subscription);
            this.subscription = {
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
            };
        },
        change(value){
           this.page = value;
            this.$store.state.suggest.page = this.page -1 ;
            this.$store.commit('refSuggestion');            
       },
       
       accept : function(sub){
           const subscription = {
                city_depart : sub.city_depart,
                city_arriv : sub.city_arriv,
                time_depart : sub.time_depart,
                time_arriv : sub.time_arriv,
                date_depart : sub.date_depart,
                n_wanted : sub.n_wanted,
                id_autocar : sub.autocar.id,
                price : sub.price,
                id_user : this.$store.state.user.id,
                type : sub.type
            };
            const id=sub.id;
            this.$emit('accept',{"id":id,"sub":subscription});
            
        },
         
    },
}
</script>
