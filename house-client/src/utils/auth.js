import Cookies from 'js-cookie'

const SystemRole = 'User-SystemRole'
const NameKey = 'User-Name'
const Id = 'User-Id'
const Token = 'User-Token'


export function setUser(systemRole,name,id,token) {  
  Cookies.set(NameKey, name)
  Cookies.set(Token, token)
  Cookies.set(SystemRole,systemRole)
  Cookies.set(Id, id)

}
export function getUser() {  
   let name = Cookies.get(NameKey)
   let systemRole = Cookies.get(SystemRole)
   let id = Cookies.get(Id)
   let token = Cookies.get(Token)
   let user = {name,systemRole,id,token}
   return user 
}
export function removeUser() {  
    Cookies.remove(NameKey)
    Cookies.remove(SystemRole)
    Cookies.remove(Id)
    Cookies.remove(Token)

 }