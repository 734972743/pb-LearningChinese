const company = {
	
	state:{
		companyId:"", //公司id
		companyName: "", //  公司名称
		companyCode: "", //  公司统一信用代码
	},
	
	
	mutations:{
		SET_COMPANY_ID(state, companyId){
			state.companyId = companyId;
		},
		
		SET_COMPANY_NAME(state, companyName){
			state.companyName = companyName;
		},
		
		SET_COMPANY_CODE(state, companyCode){
			state.companyCode = companyCode;
		}
	},
	
	actions:{
		UpdateComPanyId({commit} , companyId){
			commit("SET_COMPANY_ID", companyId);
		},
		
		UpdateComPanyName({commit} , companyName){
			commit("SET_COMPANY_NAME", companyName);
		},
		
		UpdateCompanyCode({commit} , companyCode){
			commit("SET_COMPANY_CODE", companyCode);
		}
	}
	
	
	
}

export default company;