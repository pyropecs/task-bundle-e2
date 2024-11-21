
async function getUsers(e) {
    
    const selectedBookValue = e.value;
    const bookSelectError = document.querySelector("#select-error");
    bookSelectError.classList.remove("show");
    
    const response = await fetch(`addusers/users/${selectedBookValue}`);
    const users = await response.json();
    const checkBoxes = document.querySelectorAll(".checkbox");
    const collectionOfUserIdsPresent = new Set(users.map(user=>user.id));

    checkBoxes.forEach((checkBox) => {
      const checkBoxUserId = Number(checkBox.value);
      checkBox.disabled = false;
      checkBox.checked = collectionOfUserIdsPresent.has(checkBoxUserId);
    
    });
  }
  
  document.addEventListener("DOMContentLoaded", () => {
    const messageElement = document.querySelector("#message");

    if (messageElement) {
      setTimeout(() => messageElement.textContent = "", 2500);
    }
  });
  


  function checkEmptySelected() {
    
    const bookSelect = document.querySelector("#bookselect");
    const bookSelectError = document.querySelector("#select-error");
  
    let isNotError = true;
    bookSelectError.classList.remove("show");

    if (bookSelect.value === "") {
    
      bookSelectError.textContent = "Book is required";
      bookSelectError.classList.add("show");
      isNotError = false;
    
    }
    return isNotError;
  }