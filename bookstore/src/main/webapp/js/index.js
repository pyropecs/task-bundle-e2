const formElement = document.querySelector("#createform");
const path = formElement.dataset.path;
const fields =
  path === "books"
    ? ["name", "author", "price"]
    : ["name", "department", "designation"];

document.addEventListener("DOMContentLoaded", () => {
  const messageElement = document.querySelector("#message");
  console.log(messageElement);
  if (messageElement) {
    setTimeout(() => messageElement.textContent = "", 2500);
  }
});


const fieldErrorElements = fields.map((field) =>
  document.querySelector(`#${field}-error`)
);
const fieldElements = fields.map((field) => document.querySelector(`#${field}`));

fieldErrorElements.forEach((errorElement, index) => {
  const fieldElement = fieldElements[index];
  fieldElement.addEventListener("input", () => {
    clearError(errorElement);
    if (fieldElement.id === "price") {
    validateNumber(fieldElement, "price");
    }
  });
});

function checkAllFields() {
  let isNotError = true;
  fieldElements.forEach((fieldElement, index) => {
    

    if (fieldElement.id === "price") {
      const isNumberValid = validateNumber(fieldElement, "price");
      isNotError = isNotError && isNumberValid;
    }
    const isLengthValid = validateLength(fieldElement, fields[index]);
    isNotError = isNotError && isLengthValid;
  });
  return isNotError;
}

function validateLength(element, field) {
  const errorElement = document.querySelector(`#${field}-error`);
  clearError(errorElement);
  element.value = element.value.trim();
  if (element.value.length === 0) {
    showError(errorElement, field, "is required");
    return false;
  } else if (element.value.length > 100) {
    showError(errorElement, field, "should be less than 100 characters");
    return false;
  }
  return true;
}

function validateNumber(element, field) {
  const value = Number(element.value);
  const errorElement = document.querySelector(`#${field}-error`);
  clearError(errorElement);

  if (isNaN(value)) {
    showError(errorElement, field, "should be a number");
    return false;
  } else if (value < 0) {
    showError(errorElement, field, "should be a positive number");
    return false;
  } else if (element.value.length > 7) {
    showError(errorElement, field, "should be less than 7 digits");
    return false;
  }

  return true;
}

function clearError(errorElement) {
  errorElement.classList.remove("show");
  errorElement.textContent = "";
}

function showError(errorElement, field, message) {
  errorElement.textContent = `${capitalizeFirstLetter(field)} ${message}`;
  errorElement.classList.add("show");
}

function capitalizeFirstLetter(string) {
  return string.charAt(0).toUpperCase() + string.slice(1);
}
