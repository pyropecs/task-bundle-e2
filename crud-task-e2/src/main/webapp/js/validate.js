function ValidateForm() {
  const age = Number(document.querySelector("#age").value);
  const rating = Number(document.querySelector("#rating").value);
  let errorMessage;
  let isNotError = true;
  console.log(age);
  if (age < 0 || isNaN(age)) {
    errorMessage = "Age must be a positive number.";
    const ageErrorElement = document.querySelector("#age-error");
    ageErrorElement.textContent = errorMessage;
    ageErrorElement.style.color = "red";
    isNotError = false;
  }
  if (rating < 0 || isNaN(rating)) {
    errorMessage = "Rating must be a positive number.";
    const ratingErrorElement = document.querySelector("#rating-error");
    ratingErrorElement.textContent = errorMessage;
    ratingErrorElement.style.color = "red";
    isNotError = false;
  }
  return isNotError;
}

function emptyValidation() {
  const age = document.querySelector("#age").value;
  const rating = document.querySelector("#rating").value;
  const name = document.querySelector("#name").value;
  let errorMessage;
  let isNotError = true;
  if (age.length === 0) {
    errorMessage = "Age is required";
    const ageErrorElement = document.querySelector("#age-error");
    ageErrorElement.textContent = errorMessage;
    ageErrorElement.style.color = "red";
    isNotError = false;
  }

  if (rating.length === 0) {
    errorMessage = "Rating is required";
    const ageErrorElement = document.querySelector("#rating-error");
    ageErrorElement.textContent = errorMessage;
    ageErrorElement.style.color = "red";
    isNotError = false;
  }
  if (name.length === 0) {
    errorMessage = "Name is required";
    const ageErrorElement = document.querySelector("#name-error");
    ageErrorElement.textContent = errorMessage;
    ageErrorElement.style.color = "red";
    isNotError = false;
  }
  return isNotError;
}
