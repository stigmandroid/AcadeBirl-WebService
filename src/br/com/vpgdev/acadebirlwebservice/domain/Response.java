package br.com.vpgdev.acadebirlwebservice.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {
	private String status;
	private String msg;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Response() {
	}
	
	/**
	 * Ok - Método que retorna a mensagem Ok baseado no bom funcionamento da webservice
	 * @param string
	 * @return
	 */
	public static Response Ok(String string) {
		Response r = new Response();
		r.setStatus("Ok");
		r.setMsg(string);
		return r;
	}
	

	/**
	 * Error  - Método que retorna a mensagem de erro baseado no mau funcionamento da webservice
	 * @param string
	 * @return
	 */
	public static Response Error(String string) {
		Response r = new Response();
		r.setStatus("ERROR");
		r.setMsg(string);
		return r;
	}
}
