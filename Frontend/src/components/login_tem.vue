<template>
    <div class="login_template ">
        <div style="margin-top: 100px;">
            <form class="col-md-3 card  bg-light container" style="padding: 20px;  width:  500px;" @submit.prevent="verify()">
                <h3 style="">Sign In</h3>

                <div class="form-group">
                    <label></label>
                    <input v-model="user.username" type="text" class="form-control form-control-lg" placeholder="Username" required />
                </div>

                <div class="form-group">
                    <label></label>
                    <input v-model="user.password" type="password" class="form-control form-control-lg" placeholder="Password" required/>
                </div>
                <span  class="text-center text-danger"><p >{{mymsg}}{{msg}}</p> </span>
                <button  class="btn btn-dark btn-lg btn-block">Sign In</button><br>

            </form>
        </div>
        
    </div>
</template>

<script>

export default {
    name : "login_template",
    props : ["mymsg"],
    data () {
        return{
            msg : this.mymsg,
            user : {
                username : "",
                password : "",
            },
            
        }
    },
    methods : {
        verify : async function() {
           if(this.user.password.length > 4){
                this.$emit('verify',this.user) ;
                this.msg = "";      
           }else{ 
                this.msg = "password most be > 4"; 
            }
        },
        register : function(){
            this.$router.push({ path: `/Register` })
        },
    },
    
}
</script>