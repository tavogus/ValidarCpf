package com.android.validarcpf;

import android.app.Activity;
import android.graphics.Color;
import android.net.ParseException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button btvalidar, btgerar, btsair, btlimpar;
	private EditText edcpf;
	private TextView mostracpf;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getComponets();
		validarCpf();
		gerarCpf();
		limparClick();
		sairClick();
	}
	
	private void getComponets(){
		btvalidar = (Button) findViewById(R.id.butvalidar);
		btgerar = (Button) findViewById(R.id.butgerar);
		edcpf = (EditText) findViewById(R.id.edcpf);
		mostracpf = (TextView) findViewById(R.id.mostracpf);
		btlimpar = (Button) findViewById(R.id.butlimpar);
		btsair = (Button) findViewById(R.id.butsair);
	}
	
	private void validarCpf(){
		btvalidar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(edcpf.getText().toString().isEmpty()){
					Toast.makeText(getApplicationContext(),"Informe o cpf para validar",Toast.LENGTH_LONG).show();
				}else{
					String cpf = edcpf.getText().toString();
					
					if(validarCPF(cpf)){
						mostracpf.setText("CPF VÀLIDO");
						mostracpf.setTextColor(Color.parseColor("#00FF7F"));
					}else{
						mostracpf.setText("CPF INVÀLIDO");
						mostracpf.setTextColor(Color.parseColor("#FF0000"));
					}
				}
				
			}
		});
	}
	
	private void gerarCpf(){
		btgerar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				edcpf.setText(formatar(geraCPF()));
				
			}
		});
		
	}
	
	public boolean validarCPF(String cpf){
        int soma,resto,dig;
        if(cpf.isEmpty()) return false;  
        String valor = cpf.replace(".","").replace("-","");  
        if(valor.length()!=11) return false;
        for(int i=0; i < 10;i++){
          if(valor.replace(((i+1)+""),"").length() ==0) return false;  
        }
        soma=0;
        for(int i=0; i < valor.length()-2;i++){
           soma = soma + (new Integer(valor.charAt(i)+"")) * (10-i);   
        }
        dig = ((soma % 11)<2)?0:(11-(soma % 11));
        if(dig != (new Integer(valor.charAt(9)+""))) return false;
        soma=0;
        for(int i=0; i < valor.length()-1;i++){
           soma = soma + (new Integer(valor.charAt(i)+"")) * (11-i);   
        }
        dig = ((soma % 11)<2)?0:(11-(soma % 11));
        if(dig != (new Integer(valor.charAt(10)+""))) return false;
        return true;   
  }
	
	private static String calcDigVerif(String num) {  
        Integer primDig, segDig;     
        int soma = 0, peso = 10;     
        for (int i = 0; i < num.length(); i++)     
                soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;     
        if (soma % 11 == 0 | soma % 11 == 1)     
            primDig = new Integer(0);     
        else     
            primDig = new Integer(11 - (soma % 11));     
        soma = 0;     
        peso = 11;     
        for (int i = 0; i < num.length(); i++)     
                soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;     
        soma += primDig.intValue() * 2;     
        if (soma % 11 == 0 | soma % 11 == 1)     
            segDig = new Integer(0);     
        else     
            segDig = new Integer(11 - (soma % 11));     
        return primDig.toString() + segDig.toString();     
    }     
    public static String geraCPF() {     
        String iniciais = "";     
        Integer numero;     
        for (int i = 0; i < 9; i++) {     
            numero = new Integer((int) (Math.random() * 10));     
            iniciais += numero.toString();     
        }     
        return iniciais + calcDigVerif(iniciais);     
    }  
    
    public String formatar(String cpf){
    	StringBuilder sb = new StringBuilder();
    	sb.append(cpf);
    	sb.insert(3, ".");
    	sb.insert(7, ".");
    	sb.insert(11, "-");	
    	return sb.toString();
    }

	private void sairClick() {
		btsair.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	private void limparClick(){
		btlimpar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				edcpf.setText("");
				mostracpf.setText("");
				edcpf.requestFocus();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
