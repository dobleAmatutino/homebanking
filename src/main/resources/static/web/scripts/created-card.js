const app = Vue.createApp({
    data() {
      return {
        cardType:"",
        cardColor:"",
  
      }
    },
    created() {
  
  
    },
  
  
    methods: {
        
        addCard(){
            let cardType = this.cardType
            let cardColor = this.cardColor

            return axios.post('/api/clients/current/cards',`cardType=${cardType}&cardColor=${cardColor}`)
            .then(response => window.location.href = "http://localhost:8080/web/cards.html")
            .catch(function () {


              return Swal.fire('It is not allowed to create more than 3 cards of any of the types')
            }
    
            )
        },
  

      addClient() {
  
  
        let firstName = this.firstName
        let email = this.email.toLowerCase()
        let password = this.password
        let lastName = this.lastName
  
        return axios.post('/api/clients', `firstName=${firstName}&lastName=${lastName}&email=${email}&password=${password}`)
          .then(response => axios.post('/api/login', `email=${email}&password=${password}`)//para iniciar sesion con los datos recien guardados arriba
            .then(response => window.location.href = "http://localhost:8080/web/accounts.html"))//vas a mostrar los datos de la persona que recien guardaste
  
      },
  
  
  
    },
  
  
  
  })
  app.mount('#app')