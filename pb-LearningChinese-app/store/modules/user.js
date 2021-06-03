//保存用户状态

const user = {
	
	state:{
		accessToken: "",   //用户登录token	
		
		
		//负责人信息
		chargePerson:{
			id: null,
			nickname: null,
		}
	},
	
	
	mutations:{
		SET_ACCESS_TOKEN(state, accessToken){
			state.accessToken = accessToken;
		},
		
		SET_CHARGE_PERSON(state, chargePerson){
			state.chargePerson = chargePerson;
		}
	},
	
	
	actions:{
		updatehargePerson({commit}, accessToken){
			commit("SET_ACCESS_TOKEN", accessToken);
		},
		
		updateAccessToken({commit}, chargePerson){
			commit("SET_CHARGE_PERSON", chargePerson);
		}
	}
	
	
}

export default user;