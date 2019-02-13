<h2>Android + SQLite Crud</h2>

<div>
	<img src="https://i.ibb.co/nQY0QgV/and.png" alt="and" border="0">
	<img src="https://i.ibb.co/XD0W65R/and1.png" alt="and1" border="0">
</div>

<h4>How to pass an object from activity to another</h4>

<p>Acitvity 1</p>
<p>
Intent myIntent = new Intent(MainActivty1.this, MainActivty2.class);
myIntent.putExtra("contact", Contact);
startActivity(myIntent);

or using GSON API

private Gson gson = new Gson();
Intent myIntent = new Intent(MainActivty1.this, MainActivty2.class);
myIntent.putExtra("contact", gson.toJson(contact));
startActivity(myIntent);
</p>

<p>Acitvity 2</p>
<p>myContact = (Contact) getIntent().getSerializableExtra("contact");

Contact myContact = gson.fromJson(getIntent().getStringExtra("contact"),Contact.class);
</p>
