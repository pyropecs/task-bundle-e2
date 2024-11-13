
async function getUsers(e) {
    const selectedValue = e.value;
    const bookSelectError = document.querySelector("#select-error");
    bookSelectError.classList.remove("show");
    const response = await fetch(`addusers/all/${selectedValue}`);
    const results = await response.json();
    const checkBoxes = document.querySelectorAll(".checkbox");
  
    checkBoxes.forEach((checkBox) => {
      const checkBoxValue = Number(checkBox.value);
      checkBox.disabled = false;
      const matchedResult = results.find((result) => result.id === checkBoxValue);
  
      if (matchedResult) {
        checkBox.checked = true;
      } else {
        checkBox.checked = false;
      }
    });
  }
  

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