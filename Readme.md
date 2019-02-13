<h2>Android + SQLite Crud</h2>

<div>
	<img src="https://i.ibb.co/nQY0QgV/and.png" alt="and" border="0">
	<img src="https://i.ibb.co/XD0W65R/and1.png" alt="and1" border="0">
</div>

<h3>How to pass an object from activity to another</h3>

<h4>Acitvity 1</h4>
<pre><code>
Intent myIntent = new Intent(MainActivty1.this, MainActivty2.class);
myIntent.putExtra("contact", Contact);
startActivity(myIntent);

or using GSON API

private Gson gson = new Gson();
Intent myIntent = new Intent(MainActivty1.this, MainActivty2.class);
myIntent.putExtra("contact", gson.toJson(contact));
startActivity(myIntent);
</code></pre>

<h4>Acitvity 2</h4>
<pre><code>myContact = (Contact) getIntent().getSerializableExtra("contact");

Contact myContact = gson.fromJson(getIntent().getStringExtra("contact"),Contact.class);
</code></pre>
