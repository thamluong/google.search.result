package google.search.result;

public class KeyRate {

	String key;
	int n_key;
	int rate;
	
	public KeyRate() {
		super();
		this.key = "";
		this.n_key = 0;
		this.rate = 0;
	}
	
	public KeyRate(String key, int n_key, int rate) {
		super();
		this.key = key;
		this.n_key = n_key;
		this.rate = rate;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getN_key() {
		return n_key;
	}
	public void setN_key(int n_key) {
		this.n_key = n_key;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
}
