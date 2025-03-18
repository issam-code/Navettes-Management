import { createStore } from "vuex";
import axios from "axios";
import router from "../router";
import { jwtDecode } from "jwt-decode";


export default createStore({
  state: {
    subscription:[],
    city:[],
    aurtocar:[],
    user : {},
    suggest : {
      tab : [],
      limit : 5,
      size : 0,
      page : 0,
    },
    id_user : null,
    city_depart: null,
    city_arriv: null,
    date_depart : null,
    token : localStorage.getItem("token") || '',
    limit : 3,
    size : 0,
    page : 0,

  },
  getters: {},
  mutations: {
    logout(state){
      state.token='';
      localStorage.clear("token");
      router.push("/")
    },
    async tokenHeader(state){
      axios.defaults.headers.common['Authorization'] = "Bearer " + state.token;
      const token = state.token;
      const decoded = jwtDecode(token);
      state.user =(await axios.get("http://localhost:3001/"+decoded.sub)).data
      // console.log(state.user);
    },
    async refCity(state){
      this.commit('tokenHeader')
      state.city=(await axios.get("http://localhost:3001/city/")).data
    },
    async refAutocar(state){
      this.commit('tokenHeader')
      state.autocar=(await axios.get("http://localhost:3001/autocar/")).data
    },
    async refSubscription(state){
      this.commit('tokenHeader')
      var obj =  (await axios.get("http://localhost:3001/subscription/all?type=ADMIN&limit=" + state.limit + "&page=" + state.page)).data
      state.subscription = obj[0].content;
      state.size = obj[1]
      this.commit('refCity')
    },
    async refSuggestion(state){
      this.commit('tokenHeader')
      var obj =  (await axios.get("http://localhost:3001/subscription/all?type=USER&limit=" + state.suggest.limit + "&page=" + state.suggest.page)).data
      state.suggest.tab = obj[0].content;
      state.suggest.size = obj[1]
    },
    async refadminSubscription(state){
      this.commit('tokenHeader')
      var obj =  (await axios.get("http://localhost:3001/subscription/all?limit=" + state.limit + "&page=" + state.page + "&id=" + state.user.id)).data
      state.subscription = obj[0].content;
      state.size = obj[1]
      this.commit('refCity')
    },
    async refSearch(state){
      this.commit('tokenHeader')
      var obj = (await axios.get("http://localhost:3001/subscription/all?type=ADMIN&from="+state.city_depart+"&to="+state.city_arriv+"&depart="+state.date_depart + "&limit=" + state.limit + "&page=" + state.page)).data;
      state.subscription = obj[0].content
      state.size = obj[1]
      // console.log({"dep_city":state.city_depart,"arriv_city":state.city_arriv,"date":state.date_depart })
    // console.log(obj)
    }
  },
  actions: {
    async login (_, user){
      return await axios.post("http://localhost:3001/login",user);
   },
   async add_user (_, user) {
    return await axios.post("http://localhost:3001/register", user)
  
    },
    async getuser (_, token){
      this.commit('tokenHeader')
      return await axios.get("http://localhost:3001/"+jwtDecode(token).sub);
   },
    async add_subscription (_,subscription) {
      this.commit('tokenHeader')
      return await axios.post("http://localhost:3001/subscription/",subscription);
    },
    async suggest (_,subscription) {
      this.commit('tokenHeader')
      return await axios.post("http://localhost:3001/subscription/suggest",subscription);
    },
    async update_subscription (_,c) {
      this.commit('tokenHeader')
      return await axios.put("http://localhost:3001/subscription/"+c.id ,c.sub);
    },
    async delete_subscription (_,id) {
      this.commit('tokenHeader')
      return await axios.delete("http://localhost:3001/subscription/"+id)
      
    },
    async booking (_,bookn) {
      this.commit('tokenHeader')
      return await axios.post("http://localhost:3001/booking/book",bookn)
      
    },
  },
  modules: {},
});
