<template>
     <div class="register_template">
        <div style="margin-top: 100px;">
            <form class="col-md-3 card  bg-light container" style="padding: 20px;  width:  500px;" @submit.prevent="add()" >
                <h3>Sign Up</h3>
                <div class="form-group">
                    <label></label>
                    <!-- <input v-model="user.role" type="text" class="form-control form-control-lg" required/> -->
                    <select v-model="user.role" class="form-select" id="from" aria-label="Floating label select example" required>
                        <option disabled value="">User type</option>
                        <option  value="USER">Traveler</option>
                        <option value="ADMIN">Company Agent</option>
                    </select>
                </div>

                <div class="form-group">
                    <label></label>
                    <input v-if="user.role=='ADMIN'" v-model="user.description" type="text" class="form-control form-control-lg" required placeholder="Agency name"/>
                </div>

                <div class="form-group" >
                    <label></label>
                    <input  v-model="user.username" type="text" class="form-control form-control-lg" required  placeholder="Username"/>
                </div>

                

                <div class="form-group">
                    <label></label>
                    <input v-model="user.password" type="password" class="form-control form-control-lg" required placeholder="Password" />
                </div>
                <span  class="text-center text-danger"><p>{{msg}}</p> </span>

                <button  class="btn btn-dark btn-lg btn-block">Sign Up</button><br>

                
            </form>
        </div>
        
    </div>
</template>

<script>
export default {
    name : "register_template",
    props : ["msg"],
    data () {
        return{
            user : {
                role : "",
                username : "",
                password : "",
                description:""
            }
        }
    },
    methods : {
        add : function() {
            this.$emit('add',this.user)
        }
    }
}
</script>