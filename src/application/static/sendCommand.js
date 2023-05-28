const form = document.querySelector('#myForm');
const button = document.querySelector('#send_button');


form.addEventListener('submit', event => {
  event.preventDefault();

  const otherPageUrl = "/set_command/" + button.value + "/Facebook"; // Replace with the desired URL

  form.action = otherPageUrl;
  form.submit();
});