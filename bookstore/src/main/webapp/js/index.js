const currentUrlPaths = window.location.pathname
  .split("/")
  .filter((path) => path);
const currentPath = currentUrlPaths[1];

const fields =
  currentPath === "books"
    ? ["name", "author", "price"]
    : ["name", "department", "designation"];

const fieldElements = fields.map((field) =>
  document.querySelector(`#${field}`)
);

document.addEventListener("DOMContentLoaded", () => {
  const messageElement = document.querySelector("#message");

  setTimeout(() => {
    if (messageElement) {
      messageElement.textContent = "";
    }
  }, 3000);
});

if (currentPath === "books" || currentPath === "users") {
  fieldElements.forEach((element, index) => {
    const validateLength = () => checkLengthError(element, fields[index]);
    element.addEventListener("input", validateLength);

    if (fields[index] === "price") {
      element.addEventListener("input", () => {
        validateLength();
        checkNumber(element, fields[index]);
      });
    }
  });
}

function checkAllValues() {
  let isNotError = true;

  fieldElements.forEach((fieldElement, index) => {
    const isLengthValid = checkLengthError(fieldElement, fields[index]);
    isNotError = isNotError && isLengthValid;
  });

  if (fields[2] === "price") {
    const isNumberValid = checkNumber(fieldElements[2], fields[2]);
    isNotError = isNotError && isNumberValid;
  }

  return isNotError;
}
function checkLengthError(element, field) {
  let isNotError = true;
  const errorElement = document.querySelector(`#${field}-error`);
  errorElement.classList.remove("show");

  if (element.value.length === 0) {
    const errorMessage =
      String(field).charAt(0).toUpperCase() +
      String(field).slice(1) +
      " is required";

    errorElement.textContent = errorMessage;
    errorElement.classList.add("show");
    isNotError = !isNotError;
  } else if (element.value.length > 100) {
    const errorMessage =
      String(field).charAt(0).toUpperCase() +
      String(field).slice(1) +
      " should be less than 100 characters";

    errorElement.textContent = errorMessage;
    errorElement.classList.add("show");
    isNotError = !isNotError;
  } else if (hasLeadingOrTrailingSpaces(element.value)) {
    const errorMessage = "trailing and leading spaces are not allowed";

    errorElement.textContent = errorMessage;
    errorElement.classList.add("show");
    isNotError = !isNotError;
  }

  return isNotError;
}

function hasLeadingOrTrailingSpaces(str) {
  const leadingSpace = /^\s/;
  const trailingSpace = /\s$/;

  return leadingSpace.test(str) || trailingSpace.test(str);
}

function checkEmptySelected() {
  const bookSelect = document.querySelector("#bookselect");
  const bookSelectError = document.querySelector("#select-error");
  const checkBoxes = document.querySelectorAll(".checkbox");
  let matchedLengths = 0;
  checkBoxes.forEach((checkBox)=>{
    if(checkBox.checked){
      matchedLengths++;
    }
  })

  let isNotError = true;
  bookSelectError.classList.remove("show");
  if (bookSelect.value === "") {
    bookSelectError.textContent = "Book is required";
    bookSelectError.classList.add("show");
    isNotError = false;
  }
  return isNotError;
}
function checkNumber(element, field) {
  const value = Number(element.value);

  const errorElement = document.querySelector(`#${field}-error`);
  let isNotError = true;
  errorElement.classList.remove("show");
  if (isNaN(value)) {
    const errorMessage =
      String(field).charAt(0).toUpperCase() +
      String(field).slice(1) +
      " Should be a Number";

    errorElement.textContent = errorMessage;
    errorElement.classList.add("show");
    isNotError = !isNotError;
  } else if (value < 0) {
    const errorMessage =
      String(field).charAt(0).toUpperCase() +
      String(field).slice(1) +
      " Should be a positive Number";

    errorElement.textContent = errorMessage;
    errorElement.classList.add("show");
    isNotError = !isNotError;
  } else if (element.value.length > 7) {
    const errorMessage =
      String(field).charAt(0).toUpperCase() +
      String(field).slice(1) +
      " should be less than 7 digits";

    errorElement.textContent = errorMessage;
    errorElement.classList.add("show");
    isNotError = !isNotError;
  } else if (element.value === "") {
    const errorMessage =
      String(field).charAt(0).toUpperCase() +
      String(field).slice(1) +
      " is required";

    errorElement.textContent = errorMessage;
    errorElement.classList.add("show");
    isNotError = !isNotError;
  }
  return isNotError;
}

async function getUsers(e) {
  const selectedValue = e.value;
  const bookSelectError = document.querySelector("#select-error");
  bookSelectError.classList.remove("show");
  const response = await fetch(`users/all/${selectedValue}`);
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
