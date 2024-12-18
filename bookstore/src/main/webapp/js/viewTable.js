const bookNamesTable = document.querySelector("#book-names")
const searchBookNameInput = document.querySelector("#book-input");

searchBookNameInput.addEventListener("input",getUsers)

async function getUsers() {
    
    const inputValue = searchBookNameInput.value;        
    const response = await fetch(`viewbooks/${inputValue}`);
    const books = await response.json();
    bookNamesTable.innerHTML = "";
    
    if(books.length !== 0){
    
        books.forEach((book)=>{
            bookNamesTable.innerHTML+=`<tr>
            <td>${book.name}</td>
            <td>${book.users.length !== 0 ? renderUserList(book.users):"No Users Found"}</td>
            </tr>`;

        })
    
    }else{
        bookNamesTable.innerHTML=`<tr>
                    <td>No Books found</td>
                    <td></td>
                    </tr>`;
    }
}
  

  function renderUserList(users){
    return users.map((user)=>user.name).join(", ");
  }