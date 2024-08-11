Mutation Query 

http://localhost:8080/graphql
mutation {
createPerson(personRequestModel: { name: "Aditya", age: 11, addressRequestModel: { city:"PWC", street:"D Y Road", state:"MH", pinCode:412105} }) {
personId
name
age
addressResponseModel {
addressId
city
street
state
pinCode
}
}
}
